import { deepClone } from "../../common.js";

const template = `
<div><el-alert title="条件循环语句" type="info"></el-alert></div>
`;

export default {
    props: ["data"],
    data() {
        return {
            tag: "while",
            textContext: "",
            attributes: {},
            childs: [
                {
                    tag: "condition",
                    textContext: "",
                    attributes: {},
                    childs: [],
                },
                {
                    tag: "do",
                    textContext: "",
                    attributes: {},
                    childs: [],
                }
            ]
        }
    },
    template,
    mounted() {
        this.$emit("change", deepClone(this.$data));
    }
}