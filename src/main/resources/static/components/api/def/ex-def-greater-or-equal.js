import { deepClone } from "../../common.js";

const template = `
<div><el-alert title="大于等于" type="info"></el-alert></div>
`;

export default {
    props: ["data"],
    data() {
        return { tag: "greater-or-equal", attributes: {}, childs: [], textContext: "" }
    },
    template,
    mounted() {
        this.$emit("change", deepClone(this.$data));
    }
}