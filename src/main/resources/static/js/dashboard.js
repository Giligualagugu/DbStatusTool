$(function () {


    var config = {
        type: 'line',
        data: {
            labels: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            datasets: [{
                dataIndex: 0,
                label: 'QPS',
                borderColor: '#31D5A6',
                data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                fill: true,
            }]
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: '数据库QPS'
            },
            tooltips: {
                mode: 'index',
                intersect: false,
            },
            hover: {
                mode: 'nearest',
                intersect: true
            },
            scales: {
                yAxes: [{
                    ticks: {
                        min: 0,
                        // max: 20,
                        beginAtZero: true
                    },
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Value'
                    }
                }]
            }
        }
    };
    var ctx = $("#myChart").get(0).getContext("2d");
    var myNewChart = new Chart(ctx, config);

    var flowConfig = {
        type: 'line',
        data: {
            labels: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            datasets: [{
                dataIndex: 0,
                label: '发送量',
                borderColor: '#878FF6',
                data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            }, {
                dataIndex: 1,
                label: '接收量',
                borderColor: '#5cdbd3',
                data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            }]
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: '数据流量'
            },
            tooltips: {
                mode: 'index',
                intersect: false,
            },
            hover: {
                mode: 'nearest',
                intersect: true
            },
            scales: {
                yAxes: [{
                    ticks: {
                        min: 0,
                        // max: 1000,
                        beginAtZero: true
                    },
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'bytes/second'
                    }
                }]
            }
        }
    };
    var ctx2 = $("#flowChart").get(0).getContext("2d");
    var flowChart = new Chart(ctx2, flowConfig);

    var tpsConfig = {
        type: 'line',
        data: {
            labels: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            datasets: [{
                dataIndex: 0,
                label: 'TPS',
                borderColor: '#ff85c0',
                data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                fill: true,
            }]
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: '数据库TPS'
            },
            tooltips: {
                mode: 'index',
                intersect: false,
            },
            hover: {
                mode: 'nearest',
                intersect: true
            },
            scales: {
                yAxes: [{
                    ticks: {
                        min: 0,
                        // max: 20,
                        beginAtZero: true
                    },
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Value'
                    }
                }]
            }
        }
    };
    var ctx3 = $("#tpsChart").get(0).getContext("2d");
    var tpsChart = new Chart(ctx3, tpsConfig);


    function updateSentFlow() {
        return axios.get('/chart/sentFlow');
    }

    function updateReceivedFlow() {
        return axios.get('/chart/receivedFlow');
    }

    // 更新图表数据
    function addData() {
        console.log("查询数据");
        // 跟新qps数据
        axios.get('/chart/qps').then(response => {
            let dto = response.data;
            config.data.labels.push(dto.time);
            config.data.datasets.forEach(function (dataset) {
                if (dataset.dataIndex === 0) {
                    dataset.data.push(dto.rateCount);
                }
                if (config.data.labels.length > 10) {
                    config.data.labels.shift();
                }
                if (config.data.labels.length > 10) {
                    config.data.datasets[0].data.shift();
                }
                myNewChart.update();
            });
        });


        //更新sentflow&receivedFlow数据
        axios.all([updateSentFlow(), updateReceivedFlow()])
            .then(axios.spread(function (sent, received) {
                let sentData = sent.data;
                let receData = received.data;
                flowConfig.data.labels.push(sentData.time);

                flowConfig.data.datasets.forEach(function (dataset) {
                    if (dataset.dataIndex === 0) {
                        dataset.data.push(sentData.rateCount);
                    }
                    if (dataset.dataIndex === 1) {
                        dataset.data.push(receData.rateCount);
                    }
                });

                if (flowConfig.data.labels.length > 10) {
                    flowConfig.data.labels.shift();
                }
                if (flowConfig.data.labels.length > 10) {
                    flowConfig.data.datasets[0].data.shift();
                    flowConfig.data.datasets[1].data.shift();
                }
                flowChart.update();
            }));

        //更新tps数据
        axios.get('/chart/tps').then(response => {
            let dto = response.data;
            tpsConfig.data.labels.push(dto.time);
            tpsConfig.data.datasets.forEach(function (dataset) {
                if (dataset.dataIndex === 0) {
                    dataset.data.push(dto.rateCount);
                }
            });

            if (tpsConfig.data.labels.length > 10) {
                tpsConfig.data.labels.shift();
            }
            if (tpsConfig.data.labels.length > 10) {
                tpsConfig.data.datasets[0].data.shift();
            }
            tpsChart.update();
        });

        updateTableData();
    }

    addData();
    // //5秒抓取一次数据
    setInterval(addData, 5000);


    //更新table
    function updateTableData() {

        // axios.get('/chart/status-list').then((response) => {
        //     let list = response.data;
        //     console.log("加载表格")
        //     let table = $('#dbtable');
        //     table.empty();
        //     table.append('<thead><tr><th>#</th><th>variableName</th><th>value</th></tr></thead>');
        //     var e = '';
        //     for (let i = 0; i < list.length; i++) {
        //         e += '<tr><td>' + (i + 1) + '</td><td>' + list[i].variableName + '</td><td>' + list[i].value + '</td></tr>';
        //     }
        //     table.append('<tbody>' + e + '</tbody>');
        // })

        $('#tablediv').load('/dblist');

    }


})


