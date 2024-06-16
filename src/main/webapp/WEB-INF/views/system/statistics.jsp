<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../common/include.jsp"/>

<!-- 회원 성별, 나이, 가입 시기 통계 -->
<div style="display: flex; justify-content: space-between;">
    <!-- Pie Chart -->
    <div style="width: 10%;">
        <h1>성별 통계</h1>
        <canvas id="genderChart" style="width: 10%; height: auto;"></canvas>
    </div>

    <!-- Bar Chart -->
    <div style="width: 45%;">
        <h3>나이대 통계</h3>
        <canvas id="ageChart" style="width: 400px; height: auto;"></canvas>
    </div>

    <div style="width: 45%;">
        <h3>가입시기 통계</h3>
        <canvas id="createChart" style="width: 400px; height: auto;"></canvas>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        // Gender Chart
        const genderCtx = document.getElementById('genderChart').getContext('2d');
        new Chart(genderCtx, {
            type: 'pie',
            data: {
                labels: ['Male', 'Female'],
                datasets: [{
                    label: 'Gender Distribution',
                    data: [${maleCount}, ${femaleCount}],
                    backgroundColor: ['#36A2EB', '#FF6384'],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    tooltip: {
                        callbacks: {
                            label: function (context) {
                                const label = context.label || '';
                                const value = context.raw || 0;
                                const total = context.chart.data.datasets[0].data.reduce((a, b) => a + b, 0);
                                const percentage = total ? (value / total * 100).toFixed(2) : 0;
                                return `${label}: ${value} (${percentage}%)`;
                            }
                        }
                    }
                }
            }
        });

        // Age Chart
        const ageCtx = document.getElementById('ageChart').getContext('2d');
        new Chart(ageCtx, {
            type: 'bar',
            data: {
                labels: ['0-10', '11-20', '21-30', '31-40', '41-50', '51-60', '61+'],
                datasets: [{
                    label: 'Age Distribution',
                    data: [${ageCounts[0]}, ${ageCounts[1]}, ${ageCounts[2]}, ${ageCounts[3]}, ${ageCounts[4]}, ${ageCounts[5]}, ${ageCounts[6]}],
                    backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF', '#FF9966', '#99CC00'],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });

        const createChart = document.getElementById('createChart').getContext('2d');
        new Chart(createChart, {
            type: 'bar',
            data: {
                labels: ['1971-1980', '1981-1990', '1991-2000', '2001-2010', '2011-2020', '2021-2030', '2031+'],
                datasets: [{
                    label: '가입 일시',
                    data: [${createdCounts[0]}, ${createdCounts[1]}, ${createdCounts[2]}, ${createdCounts[3]}, ${createdCounts[4]}, ${createdCounts[5]}, ${createdCounts[6]}],
                    backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF', '#FF9966', '#99CC00'],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    });
</script>

