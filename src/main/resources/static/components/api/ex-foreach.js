import { deepCloneTo, keywordColor, spaceSize } from "../common.js";

const template = `
<span>
    <span style="color: ${keywordColor};" :class="{'ex-item': hover}" @mouseover="hover=true" @mouseout="hover=false" @click="$refs.edit.open()">对象循环</span>(
    <ex-item :data="getByTag('item', data.childs)"></ex-item>
    =>
    <span>( {{data.attributes.key}} : {{data.attributes.value}} )</span>
    )
    <ex-do :data="getByTag('do', data.childs)"></ex-do>
    <ex-edit-dialog ref="edit" :data="data" @change="handleEditChange"></ex-edit-dialog>
</span>
`;

export default {
    props: ["data"],
    inject: ["getByTag"],
    template,
    data() {
        return {
            hover: false
        }
    },
    methods: {
        handleEditChange(val) {
            deepCloneTo(val, this.data);
        }
    }
}