const template = `
<span @mouseover="hover=true" @mouseleave="hover=false">
    [
    <span v-for='(item, i) in data.childs' @mouseover="mouseOverIndex=i" @mouseleave="mouseOverIndex=-1">
        <component :is="'ex-'+item.tag" :data="item"></component>
        <span v-if="i < data.childs.length-1">,</span>
        <button :style="{'display': i == mouseOverIndex?'':'none'}" @click.stop="handleAdd(i)">+</button>
        <button :style="{'display': i == mouseOverIndex?'':'none'}" @click.stop="handleDelete(i)">x</button>
    </span>
    ]
    <button v-if="!data.childs.length" :style="{'display': hover?'':'none'}" @click.stop="handleAdd(data.childs.length)">+</button>
    <ex-add-dialog ref="add" @change="handleAddChange"></ex-add-dialog>
</span>
`;

export default {
    props: ["data"],
    data() {
        return {
            index: 0,
            mouseOverIndex: -1,
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