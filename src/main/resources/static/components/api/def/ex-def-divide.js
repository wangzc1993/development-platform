import { deepClone } from "../../common.js";

const template = `
<div><el-alert title="除" type="info"></el-alert></div>
`;

export default {
    props: ["data"],
    data() {
        return { tag: "divide", attributes: {}, childs: [], textContext: "" }
    },
    template,
    mounted() {
        this.$emit("change", deepClone(this.$data));
    }
}