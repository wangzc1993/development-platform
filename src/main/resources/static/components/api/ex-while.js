
import { keywordColor } from "../common.js";

const template = `
<span>
    <span style="color: ${keywordColor};">条件循环</span>(
    <ex-condition :data="conditionTag"></ex-condition>
    )
    <ex-do :data="doTag"></ex-do>
</span>
`;

export default {
    props: ["data"],
    inject: ["getByTag"],
    template,
    data() {
        let $this = this;
        return {
            conditionTag: $this.getByTag('condition', $this.data.childs),
            doTag: $this.getByTag('do', $this.data.childs)
        }
    }
}