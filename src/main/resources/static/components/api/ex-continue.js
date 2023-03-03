import { keywordColor } from "../common.js";

const template = `
<span style="color: ${keywordColor};">跳过当前循环</span>
`;

export default {
    props: ["data"],
    template
}