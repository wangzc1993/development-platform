import { deepClone } from "../../common.js";

const template = `
<div><el-alert title="键值对" type="info"></el-alert></div>
`;

export default {
    props: ["data"],
    data() {
        return { tag: "map", attributes: {}, childs: [], textContext: "" }
    },
    template,
    mounted() {
        this.$emit("change", deepClone(this.$data));
    }
}