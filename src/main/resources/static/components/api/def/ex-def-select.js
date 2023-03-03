import { deepClone, deepCloneTo } from "../../common.js";

const template = `
<div>
    <el-alert title="选择语句" type="info"></el-alert>
    
</div>
`;

export default {
    template,
    props: ["data"],
    inject: ["getByTag"],
    data() {
        return {
            tag: "select",
            textContext: "",
            attributes: {},
            childs: [
                {
                    tag: "target",
                    textContext: "",
                    attributes: {},
                    childs: [],
                },
                {
                    tag: "case",
                    textContext: "",
                    attributes: {},
                    childs: [],
                },
                {
                    tag: "default",
                    textContext: "",
                    attributes: {},
                    childs: [],
                }
            ]
        }
    },
    computed: {
    },
    watch: {
    },
    mounted() {
        this.$emit("change", deepClone(this.$data));
        if (this.data) {
            deepCloneTo(this.data, this.$data);
        }
    }
}