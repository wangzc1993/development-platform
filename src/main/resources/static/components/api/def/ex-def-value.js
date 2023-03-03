
import { deepClone } from "../../common.js";

const template = `
<div>
    <el-alert title="变量就是可以变化的量，量指的是事物的状态，比如人的年龄、性别，游戏角色的等级、金钱等等" type="info"></el-alert>
    
    <el-form ref="form" label-width="100px">
        <el-form-item label="变量类型">
                <el-radio v-model="attributes.type" :disabled="!!data" label="name-value">定义变量</el-radio>
                <el-radio v-model="attributes.type" :disabled="!!data" label="name-ref">定义引用变量</el-radio>
                <el-radio v-model="attributes.type" :disabled="!!data" label="name-code">定义代码变量</el-radio>
                <el-radio v-model="attributes.type" :disabled="!!data" label="value">声明值</el-radio>
                <el-radio v-model="attributes.type" :disabled="!!data" label="ref">引用值</el-radio>
                <el-radio v-model="attributes.type" :disabled="!!data" label="code">声明代码</el-radio>
        </el-form-item>
        <template v-if="attributes.type == 'name-value'">
            <el-form-item label="变量名称">
                <el-input v-model="attributes.name" placeholder="请输入变量名称"></el-input>
            </el-form-item>
            <el-form-item label="变量值">
                <el-input v-model="textContext" placeholder="请输入变量值"></el-input>
            </el-form-item>
        </template>
        <template v-if="attributes.type == 'name-ref'">
            <el-form-item label="变量名称">
                <el-input v-model="attributes.name" placeholder="请输入变量名称"></el-input>
            </el-form-item>
            <el-form-item label="引用变量名称">
                <el-input v-model="attributes.ref" placeholder="请输入引用变量名称"></el-input>
            </el-form-item>
        </template>
        <template v-if="attributes.type == 'name-code'">
            <el-form-item label="变量名称">
                <el-input v-model="attributes.name" placeholder="请输入变量名称"></el-input>
            </el-form-item>
            <el-form-item label="代码">
                <el-input disabled value="添加变量后可编辑代码"></el-input>
            </el-form-item>
        </template>
        <template v-if="attributes.type == 'value'">
            <el-form-item label="变量值">
                <el-input v-model="textContext" placeholder="请输入变量值"></el-input>
            </el-form-item>
        </template>
        <template v-if="attributes.type == 'ref'">
            <el-form-item label="引用变量名称">
                <el-input v-model="attributes.ref" placeholder="请输入引用变量名称"></el-input>
            </el-form-item>
        </template>
        <template v-if="attributes.type == 'code'">
            <el-form-item label="代码">
                <el-input disabled value="添加变量后可编辑代码"></el-input>
            </el-form-item>
        </template>
    </el-form>
</div>
`;


export default {
    props: ["data"],
    data() {
        return {
            tag: "value",
            attributes: {
                type: "name-value",
                name: "",
                ref: ""
            },
            childs: [],
            textContext: ""
        }
    },
    template,
    watch: {
        "$data": {
            handler(val) {
                this.$emit("change", { ...deepClone(val) });
            },
            deep: true
        }
    },
    methods: {

    },
    mounted() {
        if (this.data) {
            let d = deepClone(this.data);
            console.log("d", d);
            for (let key in d) {
                this.$data[key] = d[key];
            }
        }
    }
}