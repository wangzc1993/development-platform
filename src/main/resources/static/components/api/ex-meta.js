import { spaceSize } from "../common.js";

const template = `
<div style="display: none">
    <p>脚本元数据: </p>
    <p v-for='item in data.childs' style="margin-left: ${spaceSize}px">
        <component :is="'ex-'+item.tag" :data="item"></component>
    </p>
</div>
`;

export default {
    props: ["data"],
    template
}