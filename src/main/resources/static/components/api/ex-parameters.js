const template = /* html */`
<span>
    <template v-for="(item,i) in data.childs">
        <component :is="'ex-'+ item.tag" :data="item"></component>
        <span v-if="i < data.childs.length-1">,</span>
    </template>
</span>
`;

export default {
    props: ["data"],
    template
}