import { deepClone } from "../../common.js";

const template = `
<div><el-alert title="用于在运行日志中输出信息" type="info"></el-alert></div>
`;
export default {
    props: ["data"],
    data() {
        return {
            tag: "print",
            attributes: {},
            textContext: "",
            childs: []
        }
    },
    template,
    mounted() {
        this.$emit("change", { ...deepClone(this.$data) });
    }
}