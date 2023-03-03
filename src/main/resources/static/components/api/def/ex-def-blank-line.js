import { deepClone } from "../../common.js";

const template = `
<div><el-alert title="空行" type="info"></el-alert></div>
`;
export default {
    props: [],
    data() {
        return { tag: "blank-line", attributes: {}, childs: [], textContext: "" }
    },
    template,
    mounted() {
        this.$emit("change", deepClone(this.$data));
    }
}