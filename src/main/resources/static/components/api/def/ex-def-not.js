import { deepClone } from "../../common.js";

const template = `
<div><el-alert title="非" type="info"></el-alert></div>
`;

export default {
    props: ["data"],
    data() {
        return { tag: "not", attributes: {}, childs: [], textContext: "" }
    },
    template,
    mounted() {
        this.$emit("change", deepClone(this.$data));
    }
}