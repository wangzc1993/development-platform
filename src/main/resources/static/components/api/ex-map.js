import { spaceSize } from "../common.js";

const template = `
<span @mouseover="hover=true" @mouseleave="hover=false">
    {
    <br>
    <span v-for='(item, i) in data.childs' style="margin-left: ${spaceSize}px">
        <component :is="'ex-'+item.tag" :data="item"></component>
        <span v-if="i < data.childs.length-1">,</span>
        <button :style="{'display': hover?'':'none'}" @click.stop="handleAdd(i)">+</button>
        <button :style="{'display': hover?'':'none'}" @click.stop="handleDelete(i)">x</button>
        <br>
    </span>
    }
    <button v-if="!data.childs.length" :style="{'display': hover?'':'none'}" @click.stop="handleAdd(data.childs.length)">+</button>
    <ex-add-dialog ref="add" @change="handleAddChange"></ex-add-dialog>
</span>
`;

export default {
    props: ["data"],
    data() {
        return {
            hover: false
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