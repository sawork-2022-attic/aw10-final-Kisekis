var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "200",
        "ok": "200",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "289",
        "ok": "289",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1952",
        "ok": "1952",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "920",
        "ok": "920",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "368",
        "ok": "368",
        "ko": "-"
    },
    "percentiles1": {
        "total": "979",
        "ok": "979",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1218",
        "ok": "1218",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1433",
        "ok": "1433",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1689",
        "ok": "1689",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 72,
    "percentage": 36
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 73,
    "percentage": 37
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 55,
    "percentage": 28
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "66.667",
        "ok": "66.667",
        "ko": "-"
    }
},
contents: {
"req_query-delivery-efbe6": {
        type: "REQUEST",
        name: "query delivery",
path: "query delivery",
pathFormatted: "req_query-delivery-efbe6",
stats: {
    "name": "query delivery",
    "numberOfRequests": {
        "total": "200",
        "ok": "200",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "289",
        "ok": "289",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1952",
        "ok": "1952",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "920",
        "ok": "920",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "368",
        "ok": "368",
        "ko": "-"
    },
    "percentiles1": {
        "total": "979",
        "ok": "979",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1218",
        "ok": "1218",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1433",
        "ok": "1433",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1689",
        "ok": "1689",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 72,
    "percentage": 36
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 73,
    "percentage": 37
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 55,
    "percentage": 28
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "66.667",
        "ok": "66.667",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
