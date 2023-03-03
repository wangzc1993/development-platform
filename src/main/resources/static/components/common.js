const spaceSize = 30;
const keywordColor = "#409EFF";
const noteColor = "#C0C4CC";
const functionColor = "#F56C6C";
const varColor = "#303133";
const valueColor = "#67C23A";

const codeOptions = [
    {
        value: 'v',
        label: '值定义',
        children: [
            {
                value: 'value',
                label: '变量'
            },
            {
                value: 'array',
                label: '列表'
            },
            {
                value: 'map',
                label: '对象'
            },
            {
                value: 'map-value',
                label: '对象键值'
            },
            {
                value: "function",
                label: "方法",
            }
        ]
    },
    {
        value: "l",
        label: "逻辑语句",
        children: [
            {
                value: "if",
                label: "判断"
            },
            {
                value: "while",
                label: "条件循环",
                children: [
                    {
                        value: "while",
                        label: "条件循环语句",
                    },
                    {
                        value: "continue",
                        label: "跳过当前循环"
                    },
                    {
                        value: "break",
                        label: "终止当前循环"
                    }
                ]
            },
            {
                value: "foreach",
                label: "对象循环",
                children: [
                    {
                        value: "foreach",
                        label: "对象循环语句",
                    },
                    {
                        value: "continue",
                        label: "跳过当前循环"
                    },
                    {
                        value: "break",
                        label: "终止当前循环"
                    }
                ]
            },
            {
                value: "for",
                label: "递进循环",
                children: [
                    {
                        value: "for",
                        label: "递进循环语句",
                    },
                    {
                        value: "continue",
                        label: "跳过当前循环"
                    },
                    {
                        value: "break",
                        label: "终止当前循环"
                    }
                ]
            },
            {
                value: "select",
                label: "选择"
            }
        ]
    },
    {
        value: "o",
        label: "其他语句",
        children: [
            {
                value: "note",
                label: "注释"
            },
            {
                value: "blank-line",
                label: "空行"
            },
            {
                value: "print",
                label: "打印日志"
            },
            {
                value: "return",
                label: "返回结果"
            }
        ]
    },
    {
        value: "e",
        label: "表达式",
        children: [
            {
                value: "add",
                label: "加"
            },
            {
                value: "subtract",
                label: "减"
            },
            {
                value: "multiply",
                label: "乘"
            },
            {
                value: "divide",
                label: "除"
            },
            {
                value: "greater-or-equal",
                label: "大于等于"
            },
            {
                value: "less-or-equal",
                label: "小于等于"
            },
            {
                value: "greater",
                label: "大于"
            },
            {
                value: "less",
                label: "小于"
            },
            {
                value: "equal",
                label: "相等"
            },
            {
                value: "not-equal",
                label: "不相等"
            },
            {
                value: "and",
                label: "与"
            },
            {
                value: "or",
                label: "或"
            },
            {
                value: "not",
                label: "非"
            }
        ]
    },
    {
        value: "r",
        label: "引用变量",
        children: [

        ]
    },
    {
        value: "rdf",
        label: "调用内部方法",
        children: []
    },
    {
        value: "rcf",
        label: "调用公共方法",
        disabled: true,
        children: [
            {
                value: "function",
                label: "转换为Json"
            }
        ]
    }
];

const deepClone = function (obj) {
    return JSON.parse(JSON.stringify(obj));
}

const deepCloneTo = function(obj, target) {
    let o = deepClone(obj);
    for (let key in o) {
        target[key] = o[key];
    }
};

export {spaceSize, keywordColor, noteColor, functionColor, varColor, valueColor, codeOptions, deepClone, deepCloneTo}