

const template = /* html */`
<span @click.stop>
    <template v-if="data.childs.length>0">
        <component :is="'ex-' + data.childs[0].tag" :data="data.childs[0]"></component>
    </template>
    <template v-else>
        <button @click.stop="handleAdd(0)">+</button>
    </template>
    <span>&gt;</span>
    <template v-if="data.childs.length>1">
        <component :is="'ex-' + data.childs[1].tag" :data="data.childs[1]"></component>
    </template>
    <template v-else>
        <button @click.stop="handleAdd(1)">+</button>
    </template>
    <ex-add-dialog ref="add" @change="handleAddChange"></ex-add-dialog>
</span>
`;


export default {
    props: ["data"],
    template,
    data() {
        return {
            index: 0,
            mouseOverIndex: -1
        }
    },
    methods: {
        handleAdd(i) {
            this.index = i;
            this.$refs.add.open();
        },
        handleDelete(i) {
            this.data.childs.splice(i, 1);
        },
        handleAddChange(val) {
            this.data.childs.splice(this.index, 0, val)
        }
    }
}