import { keywordColor, spaceSize } from "../common.js";

const template = `
<span>
    <p style="color: ${keywordColor}; margin-left: ${spaceSize}px">默认选项</p>
    <ex-row v-for="(item,i) in data.childs" :key="item" :list="data.childs" :item="item" :index="i" style="margin-left: ${spaceSize * 2}px">
        <component :is="'ex-' + item.tag" :data="item"></component>
    </ex-row>
    <p v-if="!data.childs.length" style="margin-left: ${spaceSize * 2}px">
        <button @click="$refs.add.open()">+</button>
        <ex-add-dialog ref="add" @change="handleAddChange"></ex-add-dialog>
    </p>
</span>
`;

export default {
    template,
    props: ["data"],
    methods: {
        handleAddChange(val) {
            this.data.childs.push(val);
        }
    }
}