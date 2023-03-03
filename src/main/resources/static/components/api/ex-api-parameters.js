import {keywordColor} from "../common.js";

const template = /* html */`
<span style="display: none">
    <span style="color: ${keywordColor}">接口请求参数</span>(
    <template v-for="(item,i) in data.childs">
        <component :is="'ex-' + item.tag" :data="item"></component>
        <span v-if="i<data.childs.length-1">,</span>
    </template>
    )
</span>
`;

export default {
    template,
    props: ["data"]
}