import { keywordColor, valueColor, varColor } from "../common.js";

const template = `
<span @click.stop :class="{'ex-item-error': !!check.checkLogsMap[data.id]}">
    <template v-if="data.attributes.type == 'name-value'">
        <span :class="{'ex-item': hover}" @mouseover="hover=true" @mouseout="hover=false" @click.stop="handleClick">
            <span style="color: ${keywordColor};">变量</span>
            <b style="color: ${varColor}">{{data.attributes.name}}</b> =
            <span v-if="data.textContext && isNum(data.textContext)" style="color: ${valueColor};">{{data.textContext}}</span>
            <span v-if="data.textContext && !isNum(data.textContext)" style="color: ${valueColor};">'{{data.textContext}}'</span>
        </span>
    </template>
    <template v-if="data.attributes.type == 'name-ref'">
        <span :class="{'ex-item': hover}" @mouseover="hover=true" @mouseout="hover=false" @click.stop="handleClick">
            <span style="color: ${keywordColor};">变量</span>
            <b style="color: ${varColor}">{{data.attributes.name}}</b> =
            <span v-if="data.attributes.ref"><b style="color: ${varColor}">{{data.attributes.ref}}</b></span>
        </span>
    </template>
    <template v-if="data.attributes.type == 'name-code'">
        <span :class="{'ex-item': hover}" @mouseover="hover=true" @mouseout="hover=false" @click.stop="handleClick">
            <span style="color: ${keywordColor};">变量</span>
            <b style="color: ${varColor}">{{data.attributes.name}}</b> =
            <template v-if="!data.childs.length">
                <button @click.stop="$refs.add.open()">插入代码</button>
            </template>
        </span>
        <template v-if="data.childs.length">
            <component :is="'ex-'+data.childs[0].tag" :data="data.childs[0]"></component>
        </template>
    </template>
    <template v-if="data.attributes.type == 'value'">
        <span :class="{'ex-item': hover}" @mouseover="hover=true" @mouseout="hover=false" @click.stop="handleClick">
            <span v-if="data.textContext && isNum(data.textContext)" style="color: ${valueColor};">{{data.textContext}}</span>
            <span v-if="data.textContext && !isNum(data.textContext)" style="color: ${valueColor};">'{{data.textContext}}'</span>
        </span>
    </template>
    <template v-if="data.attributes.type == 'ref'">
        <span :class="{'ex-item': hover}" @mouseover="hover=true" @mouseout="hover=false" @click.stop="handleClick">
            <span><b style="color: ${varColor}">{{data.attributes.ref}}</b></span>
        </span>
    </template>
    <template v-if="data.attributes.type == 'code'">
        <template v-if="data.childs.length">
            <component :is="'ex-'+data.childs[0].tag" :data="data.childs[0]"></component>
        </template>
        <template v-if="!data.childs.length">
            <button @click.stop="$refs.add.open()">插入代码</button>
        </template>
    </template>
    
    <ex-edit-dialog ref="edit" :data="data" @change="handleDefChange"></ex-edit-dialog>
    <ex-add-dialog ref="add" @change="handleAddChange"></ex-add-dialog>
</span>
`;

export default {
    template,
    props: ["data"],
    inject: ["check"],
    data() {
        return {
            hover: false
        }
    },
    watch: {
        check(val) {
            console.log("check", val);
        }
    },
    methods: {
        isNum(val) {
            return !isNaN(parseFloat(val)) && isFinite(val);
        },
        handleClick() {
            this.$refs.edit.open();
        },
        handleDefChange(val) {
            this.data.childs = val.childs;
            this.data.textContext = val.textContext;
            this.data.attributes = val.attributes;
        },
        handleAddChange(val) {
            this.data.childs.push(val);
        }
    }
}