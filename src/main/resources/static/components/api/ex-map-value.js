const template = `
<span>{{data.attributes.key}} : 
    <component v-if='data.childs.length' :is="'ex-' + data.childs[0].tag" :data="data.childs[0]"></component>
    <button v-else @click.stop="$refs.add.open()">+</button>
    <ex-add-dialog ref="add" @change="handleAddChange"></ex-add-dialog>
</span>
`;

export default {
    props: ["data"],
    template,
    methods: {
        handleAddChange(val) {
            this.data.childs.push(val);
        }
    }
}