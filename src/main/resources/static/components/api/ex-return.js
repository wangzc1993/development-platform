import { keywordColor } from "../common.js";

const template = `
<span>
    <span style="color: ${keywordColor};">返回</span>
    <template v-if="data.childs.length">
        <component :is="'ex-'+ data.childs[0].tag" :data="data.childs[0]"></component>
    </template>
    <template v-else>
        <button @click="$refs.add.open()">+</button>
        <ex-add-dialog ref="add" @change="handleAddChange"></ex-add-dialog>
    </template>
</span>
`;

export default {
    template,
    props: ["data"],
    methods: {
        handleAddChange(val) {
            this.data.childs.push(val);
            console.log(this.data.childs);
        }
    }
}