<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Charts</h1>
    <p class="mb-4">Fruit Store Analytics.</p>

    <!-- Content Row -->
    <div class="row">

        <div class="col-xl-8 col-lg-7">

            <!-- Area Chart -->
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Monthly Revenue</h6>
                </div>
                <div class="card-body">
                    <button class="btn btn-outline-primary"
                        onclick="buildChart1(${sessionScope.revenueJsMonthArray}, ${revenueJsTotalMoneyMonthlyArray})">
                        <i class="bi bi-eye"></i> See Chart
                    </button>
                    <div class="chart-area">
                        <canvas id="revenueInMonthsAreaChart"></canvas>
                    </div>
                    <hr>
                </div>
            </div>

            <!-- Bar Chart -->
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Top 5 Users</h6>
                </div>
                <div class="card-body">
                    <button class="btn btn-outline-primary"
                        onclick="buildChart2(${sessionScope.topUsersJsArray}, ${topUsersTotalMoneyJsArray})">
                        <i class="bi bi-eye"></i> See Chart
                    </button>
                    <div class="chart-bar">
                        <canvas id="revenueInMonthsBarChart"></canvas>
                    </div>
                    <hr>
                </div>
            </div>

        </div>

        <!-- Donut Chart -->
        <div class="col-xl-4 col-lg-5">
            <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Top 5 Products</h6>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                    <button class="btn btn-outline-primary"
                        onclick="buildChart3(${sessionScope.top5ProductJsArray}, ${top5ProductQuantityJsArray})">
                        <i class="bi bi-eye"></i> See Chart
                    </button>
                    <div class="chart-pie pt-4">
                        <canvas id="topProductPieChart"></canvas>
                    </div>
                    <hr>
                </div>
            </div>
        </div>
    </div>

</div>

