import { keywordColor, spaceSize } from "../common.js";

const template = `
<span>
    <ex-row style="margin-left: ${spaceSize}px">
        <span style="color: ${keywordColor};">选项</span> 
        <ex-target :data="target"></ex-target>:
    </ex-row>
    <p style="margin-left: ${spaceSize}px">
        <ex-do :data="doItem"></ex-do>
    </p>
</span>
`;

export default {
    template,
    props: ["data"],
    inject: ["getByTag"],
    computed: {
        target() {
            return this.getByTag("target", this.data.childs);
        },
        doItem() {
            return this.getByTag("do", this.data.childs);
        }
    },
    methods: {
        handleAddChange(val) {
            this.data.childs.push(val);
        }
    }
}