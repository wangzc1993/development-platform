import { keywordColor } from "../common.js";

const template = `
<span>
    <span style="color: ${keywordColor};">打印</span>
    <span v-for="(item,i) in data.childs" @mouseover="mouseOverIndex = i" @mouseout="mouseOverIndex = -1">
        <component :is="'ex-' + item.tag" :data="item"></component>
        <button :style="{'display': mouseOverIndex == i? '' : 'none'}" @click="handleAdd(i)">+</button>
        <button :style="{'display': mouseOverIndex == i? '' : 'none'}" @click="handleDelete(i)">x</button>
        <span v-if="i < data.childs.length-1">,</span>
    </span>
    <button v-if="!data.childs.length" @click="handleAdd(data.childs.length)">+</button>
    <ex-add-dialog ref="add" @change="handleAddChange"></ex-add-dialog>
</span>
`;

export default {
    props: ["data"],
    data() {
        return {
            index: -1,
            hover: false,
            mouseOverIndex: -1
        }
    },
    template,
    methods: {
        handleAdd(i) {
            this.index = i;
            this.$refs.add.open();
        },
        handleDelete(i) {
            this.data.childs.splice(i, 1);
        },
        handleAddChange(val) {
            this.data.childs.splice(this.index + 1, 0, val);
        }
    }
}