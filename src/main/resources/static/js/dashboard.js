$(function () {
    var config = {
        type: 'line',
        data: {
            labels: [],
            datasets: [{
                label: 'qps',
                backgroundColor: 'rgb(29,119,151)',
                borderColor: 'rgb(13,109,143)',
                data: [],
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
                xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Time'
                    }
                }],
                yAxes: [{
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
    // This will get the first returned node in the jQuery collection.
    var myNewChart = new Chart(ctx, config);

    var flowConfig = {
        type: 'line',
        data: {
            labels: [],
            datasets: [{
                label: 'sentflow',
                backgroundColor: 'rgb(29,119,151)',
                borderColor: 'rgb(13,109,143)',
                data: [],
                fill: true,
            }]
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: '数据库发送量'
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
                xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Time'
                    }
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Value'
                    }
                }]
            }
        }
    };
    var ctx2 = $("#flowChart").get(0).getContext("2d");
    var flowChart = new Chart(ctx2, flowConfig);


    //添加数据
    $("#addData").bind('click', function () {
        console.log("查询数据");

        // 跟新qps数据
        axios.get('/chart/qps').then(response => {
            console.log(response.data);
            let dto = response.data;
            config.data.labels.push(dto.time);
            config.data.datasets.forEach(function (dataset) {
                if (dataset.label === 'qps') {
                    dataset.data.push(dto.rateCount);
                }
            });
        });

        myNewChart.update();
        if (config.data.labels.length > 10) {
            config.data.labels.shift();
        }
        if (config.data.labels.length > 11) {
            config.data.datasets[0].data.shift();
        }
        //更新sentflow数据
        axios.get('/chart/sentFlow').then(response => {
            console.log(response.data);
            let dto = response.data;
            flowConfig.data.labels.push(dto.time);
            flowConfig.data.datasets.forEach(function (dataset) {
                if (dataset.label === 'sentflow') {
                    dataset.data.push(dto.rateCount);
                }
            });
        });


        flowChart.update();
        if (flowConfig.data.labels.length > 10) {
            flowConfig.data.labels.shift();
        }
        if (flowConfig.data.labels.length > 11) {
            flowConfig.data.datasets[0].data.shift();
        }

    });
})


