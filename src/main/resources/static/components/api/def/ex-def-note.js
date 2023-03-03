import { deepClone } from "../../common.js";

const template = `
<div>
    <el-alert
        title="对代码进行解释和说明。目的是为了让别人和自己很容易看懂，一看就知道这段代码是做什么用的"
        type="info">
    </el-alert>
    <el-input v-model="textContext" placeholder="请输入注释"></el-input>
</div>
`;

export default {
    props: ["data"],
    data() {
        return { tag: "note", attributes: {}, childs: [], textContext: "" }
    },
    template,
    watch: {
        textContext(val) {
            this.$emit("change", { ...deepClone(this.$data) });
        }
    },
    mounted() {
        if (this.data) {
            this.textContext = this.data.textContext;
        }
    }
}