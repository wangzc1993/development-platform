const template = `
<div>
    <ex-row v-for='(item,i) in data.childs' :key="item" :list="data.childs" :item="item" :index="i">
        <component :is="'ex-'+item.tag" :data="item"></component>
    </ex-row>
</div>
`;

export default {
    props: ["data"],
    template
}