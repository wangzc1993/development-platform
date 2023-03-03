import { deepClone } from "../../common.js";

const template = `
<div><el-alert title="判断语句" type="info"></el-alert></div>
`;

export default {
    props: ["data"],
    data() {
        return {
            tag: "if",
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
                },
                {
                    tag: "else",
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