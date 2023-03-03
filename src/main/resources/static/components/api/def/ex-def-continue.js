import { deepClone } from "../../common.js";

const template = `
<div><el-alert title="跳过循环" type="info"></el-alert></div>
`;

export default {
    props: ["data"],
    data() {
        return { tag: "continue", attributes: {}, childs: [], textContext: "" }
    },
    template,
    mounted() {
        this.$emit("change", deepClone(this.$data));
    }
}