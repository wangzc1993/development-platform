import { keywordColor, deepCloneTo } from "../common.js";

const template = `
<span>
    <span style="color: ${keywordColor};" :class="{'ex-item': hover}" @mouseover="hover=true" @mouseout="hover=false" @click="$refs.edit.open()">递进循环</span>(
    <ex-start :data="start"></ex-start>
    ,
    <span style="color: ${keywordColor};">条件</span>
    <ex-condition :data="condition"></ex-condition>
    ,
    <ex-step :data="step"></ex-step>
    )
    <ex-do :data="doItem"></ex-do>
    <ex-edit-dialog ref="edit" :data="data" @change="handleEditChange"></ex-edit-dialog>
</span>
`;

export default {
    template,
    props: ["data"],
    inject: ["getByTag"],
    data() {
        return {
            hover: false
        }
    },
    computed: {
        start() {
            return this.getByTag('start', this.data.childs)
        },
        condition(){
            return this.getByTag('condition', this.data.childs)
        } ,
        step(){
            return this.getByTag('step', this.data.childs)
        } ,
        doItem() {
            return this.getByTag('do', this.data.childs)
        },
    },
    methods: {
        handleEditChange(val) {
            deepCloneTo(val, this.data);
            console.log(this.data);
            this.$nextTick(function() {
                this.$forceUpdate();
            });
        }
    }
}