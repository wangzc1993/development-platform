import { deepClone, deepCloneTo } from "../../common.js";

const template = `
<div>
    <el-alert title="递进循环语句" type="info"></el-alert>
    <el-form ref="form" label-width="100px">
        <el-form-item label="开始变量名称">
            <el-input v-model="startItem.attributes.name" placeholder="请输入开始变量名称"></el-input>
        </el-form-item>
        <el-form-item label="递进值">
            <el-input v-model="stepItem.textContext" placeholder="请输入递进值"></el-input>
        </el-form-item>
    </el-form>
</div>
`;

export default {
    template,
    props: ["data"],
    inject: ["getByTag"],
    data() {
        return {
            tag: "for",
            textContext: "",
            attributes: {},
            childs: [
                {
                    tag: "start",
                    textContext: "",
                    attributes: {},
                    childs: [],
                },
                {
                    tag: "condition",
                    textContext: "",
                    attributes: {},
                    childs: [],
                },
                {
                    tag: "step",
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
    computed: {
        startItem() {
            return this.getByTag('start', this.childs);
        },
        stepItem() {
            return this.getByTag('step', this.childs);
        }
    },
    watch: {
        "$data": {
            handler(val) {
                this.$emit("change", deepClone(val));
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