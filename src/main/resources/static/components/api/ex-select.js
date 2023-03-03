
import { keywordColor, spaceSize } from "../common.js";

const template = `
<span>
    <span style="color: ${keywordColor};">选择</span>(
    <ex-target :data="target"></ex-target>
    )
    <br/>
    <template v-for="item in caseList">
        <ex-case :data="item"></ex-case>
    </template>
    <template v-if="defaultItem">
        <ex-default :data="defaultItem"></ex-default>
    </template>
</span>
`;

export default {
    template,
    props: ["data"],
    inject: ["getByTag", "getListByTag"],
    data() {
        return {
            
        }
    },
    computed: {
        target() {
            return this.getByTag('target', this.data.childs);
        },
        defaultItem() {
            return this.getByTag('default', this.data.childs)
        },
        caseList() {
            return this.getListByTag("case", this.data.childs);
        }
    },
}