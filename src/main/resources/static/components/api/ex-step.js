import { keywordColor, varColor } from "../common.js";
const template = `
<span>
    <span style="color: ${keywordColor};">递进</span>
    <span>{{data.textContext}}</span>
</span>
`;

export default {
    template,
    props: ["data"],
}