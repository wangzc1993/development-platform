import { keywordColor, varColor } from "../common.js";
const template = `
<span>
    <span style="color: ${keywordColor};">开始</span>
    <b style="color: ${varColor}px">{{data.attributes.name}}</b> = 
    <component v-if="data.childs.length" :is="'ex-'+data.childs[0].tag" :data="data.childs[0]"></component>
    <button v-else>+</button>
</span>
`;

export default {
    template,
    props: ["data"],
}