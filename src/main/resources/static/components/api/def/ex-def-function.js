import { deepClone, deepCloneTo } from "../../common.js";

const template = /* html */`
<div>
    <el-alert v-if="attributes.type=='def'" title="定义方法" type="info"></el-alert>
    <el-alert v-if="attributes.type=='ref'" title="调用方法，参数数量请根据调用方法的参数数量自行添加" type="info"></el-alert>
    <el-form ref="form" label-width="100px">
        <el-form-item label="方法类型">
                <el-radio v-model="attributes.type" :disabled="!!data" label="def">定义方法</el-radio>
                <el-radio v-model="attributes.type" :disabled="!!data" label="ref">调用方法</el-radio>
        </el-form-item>
        <template v-if="attributes.type=='def'">
            <el-form-item label="方法名称">
                <el-input v-model="attributes.name" placeholder="请输入方法名称"></el-input>
            </el-form-item>
            <el-form-item v-for="(item,i) in parameters.childs" :label="'参数 ' + (i + 1)" >
                <el-input v-model="item.attributes.name" placeholder="请输入参数名称">
                    <el-button slot="append" @click.prevent="deleteParameter(i)">删除</el-button>
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-button @click.prevent="addParameter">新增参数</el-button>
            </el-form-item>
        </template>
        <template v-if="attributes.type=='ref'">
            <el-form-item label="调用方法名称">
                <el-input v-model="attributes.ref" placeholder="调用方法名称"></el-input>
            </el-form-item>
            <el-form-item v-for="(item,i) in parameters.childs" :label="'参数 ' + (i + 1)" >
                <el-input value="参数值待添加调用方法后设置" disabled placeholder="请输入参数名称">
                    <el-button slot="append" @click.prevent="deleteParameter(i)">删除</el-button>
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-button @click.prevent="addParameter">新增参数</el-button>
            </el-form-item>
        </template>
    </el-form>
</div>
`;

export default {
    template,
    props: ["data"],
    inject: ["getByTag"],
    data() {
        return {
            tag: "function",
            textContext: "",
            attributes: {type: ""},
            childs: [
                {
                    tag: "parameters",
                    textContext: "",
                    attributes: {},
                    childs: [],
                },
                {
                    tag: "do",
                    textContext: "",
                    attributes: {},
                    childs: [],
                }
            ]
        }
    },
    computed: {
        parameters() {
            return this.getByTag('parameters', this.childs);
        },
        doItem() {
            return this.getByTag('do', this.childs);
        }
    },
    watch: {
        "$data": {
            handler(val) {
                this.$emit("change", deepClone(val));
            },
            deep: true
        }
    },
    mounted() {
        this.$emit("change", deepClone(this.$data));
        if (this.data) {
            deepCloneTo(this.data, this.$data);
        }
    },
    methods: {
        addParameter() {
            if (this.attributes.type=="def") {
                this.parameters.childs.push({
                    tag: "parameter",
                    textContext: "",
                    attributes: {type: "def", name: ""},
                    childs: []
                })
            }if (this.attributes.type=="ref") {
                this.parameters.childs.push({
                    tag: "parameter",
                    textContext: "",
                    attributes: {type: "ref"},
                    childs: []
                })
            }
        },
        deleteParameter(i) {
            this.parameters.childs.splice(i, 1);
        }
    }
}