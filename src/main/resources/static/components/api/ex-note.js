import { noteColor } from "../common.js";

const template = `
<div>
    <span style="color: ${noteColor};" :class="{'ex-item': hover}" 
        @mouseover="hover=true" @mouseout="hover=false" 
        @click.stop="$refs.edit.open(data)">{{data.textContext}}</span>
    <ex-edit-dialog ref="edit" :data="data" @change="handleDefChange"></ex-edit-dialog>
</div>
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
        handleDefChange(val) {
            this.$refs.edit.close();
            this.data.textContext = val.textContext;
        }
    }
}