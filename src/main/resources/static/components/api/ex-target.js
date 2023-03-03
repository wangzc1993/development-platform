const template = `
<span>
    <component v-if="data.childs.length" :is="'ex-'+data.childs[0].tag" :data="data.childs[0]"></component>
    <button v-else @click="$refs.add.open()">+</button>
    <ex-add-dialog ref="add" @change="handleAddChange"></ex-add-dialog>
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