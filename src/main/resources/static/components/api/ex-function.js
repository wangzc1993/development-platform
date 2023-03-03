import { keywordColor, functionColor, spaceSize, deepCloneTo } from "../common.js";

const template = /* html */`
<span>
    <template v-if="data.attributes.type=='def'">
        <span :class="{'ex-item': hover}" @mouseover="hover=true" @mouseout="hover=false" @click="$refs.edit.open()">
            <span style="color: ${keywordColor};">方法</span>
            <b style="color: ${functionColor};">{{data.attributes.name}}</b>(
            <ex-parameters :data="parameters"></ex-parameters>
            )
        </span>
        <ex-do :data="doItem"></ex-do>
    </template>
    <template v-if="data.attributes.type=='ref'">
        <b style="color: ${functionColor}" :class="{'ex-item': hover}" 
        @mouseover="hover=true" @mouseout="hover=false" @click="$refs.edit.open()">{{data.attributes.ref}}</b>(
        <ex-parameters :data="parameters"></ex-parameters>
        )
    </template>
    <ex-edit-dialog ref="edit" :data="data" @change="handleEditChange"></ex-edit-dialog>
</span>
`;

export default {
    template,
    props: ["data"],
    inject: ["getByTag", "getListByTag"],
    data() {
        return {
            hover: false
        }
    },
    computed: {
        parameters() {
            return this.getByTag('parameters', this.data.childs);
        },
        doItem() {
            return this.getByTag('do', this.data.childs);
        }
    },
    methods: {
        handleEditChange(val) {
            deepCloneTo(val, this.data);
        }
    }
}