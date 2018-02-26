<template>
    <div>
        <br/>
        <div class="col-lg-12">
            <div class="ibox">
                <div class="ibox-content">
                    <div class="col-md-1"></div>
                    <div class="col-md-2">
                        <p style="margin-top: 6px" class="nav navbar-nav navbar-left">
                            关键词: </p>
                    </div>
                    <div class="col-md-8">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="宿主机ip">
                            <span class="input-group-btn">
                                            <button class="btn btn-default" type="button">
                                                <i class="fa fa-search"></i>
                                            </button>
                                        </span>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                    <br/> <br/>
                </div>
            </div>
        </div>
        <div class="col-lg-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5><i class="fa fa-bar-chart"></i> 容器列表</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <v-table
                            :multiple-sort="false"
                            is-horizontal-resize
                            column-width-drag
                            style="width:100%"
                            :show-vertical-border="false"
                            :columns="tableConfig.columns"
                            :table-data="tableConfig.tableData"
                            row-hover-color="#eee"
                            row-click-color="#edf7ff"
                            @sort-change="sortChange"
                            @on-custom-comp="customCompFunc"
                            :paging-index="(pageIndex-1)*pageSize"
                    />
                    <br/>
                    <v-pagination @page-change="pageChange" @page-size-change="pageSizeChange"
                                  :total="total"
                                  :layout="['total', 'prev', 'pager', 'next', 'sizer', 'jumper']"/>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import 'vue-easytable/libs/themes-base/index.css'
    import {VTable, VPagination} from 'vue-easytable'
    import Vue from 'vue'

    export default {
        components: {VTable, VPagination},//组件注册
        data: function () {
            return {
                pageIndex: 1,
                pageSize: 10,
                total: 0,
                tableConfig: {
                    multipleSort: false,
                    tableData: [],
                    columns: [
                        {
                            field: 'id',
                            title: '序号',
                            width: 80,
                            titleAlign: 'center',
                            columnAlign: 'center',
                            isResize: true
                        },
                        {
                            field: 'cid',
                            title: '容器ID',
                            width: 100,
                            titleAlign: 'center',
                            columnAlign: 'center',
                            isResize: true
                        },
                        {
                            field: 'ip',
                            title: '容器IP',
                            width: 80,
                            titleAlign: 'center',
                            columnAlign: 'center',
                            isResize: true
                        },
                        {
                            field: 'name',
                            title: '容器名称',
                            width: 80,
                            titleAlign: 'center',
                            columnAlign: 'center',
                            isResize: true
                        },
                        {
                            field: 'time',
                            title: '创建时间',
                            width: 80,
                            titleAlign: 'center',
                            columnAlign: 'center',
                            isResize: true
                        },
                        {
                            field: 'status',
                            title: '运行状态',
                            width: 80,
                            titleAlign: 'center',
                            columnAlign: 'center',
                            isResize: true
                        },
                        {
                            field: 'services',
                            title: '服务运行状态',
                            width: 80,
                            titleAlign: 'center',
                            columnAlign: 'center',
                            isResize: true
                        },
                        {
                            field: 'hip',
                            title: '宿主机IP',
                            width: 80,
                            titleAlign: 'center',
                            columnAlign: 'center',
                            isResize: true
                        },
                        {
                            field: 'operation',
                            title: '操作',
                            width: 100,
                            titleAlign: 'center',
                            columnAlign: 'center',
                            componentName: 'table-operation',
                            isResize: true
                        }
                    ]
                }
            }
        },
        methods: {
            getTableData: function () {
                let _self = this;
                $.getJSON("static/ajax/containerList.json", "", function(data) {
                    if (data.code) {
                        _self.tableConfig.tableData = data.content.data;
                        _self.total = data.content.total;
                    } else {
                        // notyWarning(data.content);
                    }
                });
            },
            //刷新
            refashTable: function () {
                this.tableConfig.tableData =  JSON.parse(JSON.stringify(this.tableConfig.tableData));
            },
            customCompFunc: function (params) {

                if (params.type === 'release') {

                    $('#app-release').modal('show');
                } else if (params.type === 'service') {

                    $('#app-service').modal('show');
                } else if (params.type === 'deleteRow') {

                    $('#app-delete').modal('show');
                } else if (params.type === 'edit') {

                    $('#app-edit').modal('show');

                }
            },
            pageChange: function (pageIndex) {
                this.pageIndex = pageIndex;
                this.getTableData();
            },
            pageSizeChange: function (pageSize) {
                this.pageIndex = 1;
                this.pageSize = pageSize;
                this.getTableData();
            },
            sortChange: function (params) {
                if (params.name !== '') {
                    this.sortBy = "name";
                    this.sortDirection = params.name;
                } else if (params.shortName !== '') {
                    this.sortBy = "short_name";
                    this.sortDirection = params.shortName;
                }
                this.getTableData();
            }
        },
        created: function () {
            //更新表格数据
            this.getTableData();
        },
        updated: function () {
            $(function () {
                $("[data-toggle='tooltip']").tooltip();
            });

            $(function () {
                $("[data-toggle='popover']").popover({ trigger: "hover" });
            });
        }
    }


    // 自定义列组件
    Vue.component('table-operation', {
        template: '<span>' +
        '<button class="btn btn-info btn-sm btn-alt btn-round tip" data-toggle="tooltip" data-placement="top" title="启动" @click.stop.prevent="edit(rowData,index)"><i class="fa fa-edit"></i></button>&nbsp;' +
        '<button class="btn btn-warning btn-sm btn-alt btn-round" data-toggle="tooltip" data-placement="top" title="停止" @click.stop.prevent="deleteRow(rowData,index)"><i class="fa fa-trash"></i></button>' +
        '<button class="btn btn-info btn-sm btn-alt btn-round" data-toggle="tooltip" data-placement="top" title="重启服务" @click.stop.prevent="deleteRow(rowData,index)"><i class="fa fa-trash"></i></button>' +
        '</span>',
        props: {
            rowData: {
                type: Object
            },
            field: {
                type: String
            },
            index: {
                type: Number
            }
        },
        methods: {
            edit: function () {
                var params = {type: 'edit', index: this.index, rowData: this.rowData};
                this.$emit('on-custom-comp', params);
            },
            deleteRow: function () {
                var params = {type: 'deleteRow', index: this.index, rowData: this.rowData};
                this.$emit('on-custom-comp', params);
            }
        }
    });
</script>

<style scoped>

</style>