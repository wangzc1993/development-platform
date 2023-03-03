
import { keywordColor, spaceSize } from "../common.js";

const template = `
<span>
    <span style="color: ${keywordColor};">判断</span>(
    <ex-condition :data="condition"></ex-condition>
    )
    <ex-do :data="doItem"></ex-do>
    <template v-if="elseItem">
        <p style="color: ${keywordColor};">否则</p>
        <ex-else :data="elseItem"></ex-else>
    </template>
</span>
`;

export default {
    props: ["data"],
    inject: ["getByTag"],
    data() {
        let $this = this;
        let childs = $this.data.childs;
        return {
            condition: $this.getByTag('condition', childs),
            doItem: $this.getByTag('do', childs),
            elseItem: $this.getByTag('else', childs),
        }
    },
    template
}