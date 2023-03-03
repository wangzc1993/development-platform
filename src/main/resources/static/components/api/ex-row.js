import { deepClone, codeOptions } from "../common.js";

const template = `
<p :class="{'ex-row': hover}" @mouseover.stop="handleMouseover" @mouseout.stop="hover=false" @click.stop>
    <el-button-group :style="{'float': 'right', 'display': hover ? 'unset':'none'}">
        <el-button size="mini" type="success" @click.stop="handleInsertClick">下方插入行</el-button>
        <el-button size="mini" type="danger" @click.stop="handleDeleteClick">删除</el-button>
    </el-button-group>
    <ex-add-dialog ref="add" @change="handleChange"></ex-add-dialog>
    <slot></slot>
</p>
`;

export default {
    props: ["list", "item", "index"],
    data() {
        return {
            hover: false,
            options: codeOptions
        }
    },
    template,

    methods: {
        handleMouseover($event) {
            this.hover = true;

        },
        handleMouseout() {
            this.hover = false;
        },
        handleInsertClick($event) {
            this.$refs.add.open();

        },
        handleDeleteClick() {
            this.list.splice(this.index, 1);
        },
        handleChange(data) {
            this.list.splice(this.index + 1, 0, deepClone(data));
        }
    }
}