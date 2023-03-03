import { deepClone, deepCloneTo } from "../../common.js";

const template = `
<div>
    <el-alert title="对象循环语句" type="info"></el-alert>
    <el-form ref="form" label-width="100px">
        <el-form-item label="键变量名称">
            <el-input v-model="attributes.key" placeholder="请输入键变量名称"></el-input>
        </el-form-item>
        <el-form-item label="值变量名称">
            <el-input v-model="attributes.value" placeholder="请输入值变量名称"></el-input>
        </el-form-item>
    </el-form>
</div>
`;

export default {
    props: ["data"],
    data() {
        return {
            tag: "foreach",
            textContext: "",
            attributes: {key: "", value: ""},
            childs: [
                {
                    tag: "item",
                    textContext: "",
                    attributes: {},
                    childs: [],
                },
                {
                    tag: "do",
                    textContext: "",
                    attributes: {},
                    childs: [],
                }
            ]
        }
    },
    template,
    watch: {
        "$data": {
            handler(val) {
                this.$emit("change", deepClone(this.$data));
            },
            deep: true
        }
    },
    mounted() {
        this.$emit("change", deepClone(this.$data));
        if (this.data) {
            deepCloneTo(this.data, this.$data);
        }
    }
}