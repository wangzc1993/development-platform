import { deepClone } from "../../common.js";

const template = `
<div><el-alert title="返回" type="info"></el-alert></div>
`;

export default {
    props: ["data"],
    data() {
        return { tag: "return", attributes: {}, childs: [], textContext: "" }
    },
    template,
    mounted() {
        this.$emit("change", deepClone(this.$data));
    }
}