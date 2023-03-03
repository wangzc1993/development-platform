import { deepClone } from "../../common.js";

const template = `
<div><el-alert title="数组" type="info"></el-alert></div>
`;

export default {
    props: ["data"],
    data() {
        return { tag: "array", attributes: {}, childs: [], textContext: "" }
    },
    template,
    mounted() {
        this.$emit("change", deepClone(this.$data));
    }
}