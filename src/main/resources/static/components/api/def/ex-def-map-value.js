import { deepClone } from "../../common.js";

const template = `
<div>
    <el-alert title="键值对" type="info"></el-alert>
    <el-form ref="form" label-width="100px">
        <el-form-item label="键名称">
            <el-input v-model="attributes.key" placeholder="请输入键名称"></el-input>
        </el-form-item>
    </el-form>
</div>
`;

export default {
    props: ["data"],
    data() {
        return { tag: "map-value", attributes: { key: "" }, childs: [], textContext: "" }
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
    }
}