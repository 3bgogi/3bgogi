<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		
		var totalCounting = new Array();
		var ssName = new Array();
		
		<c:forEach var="deliverylist" items="${deliveryList}">
			totalCounting.push('${deliverylist.total_counting}');
			ssName.push('${deliverylist.ss_name}');
		</c:forEach>
		var chart = new Chartist.Pie('.ct-chart-category', {
	        series: totalCounting,
	        labels: ssName
	    }, {
	        donut: true,
	        showLabel: true,
	        donutWidth: 50

	    });

	    chart.on('draw', function(data) {
	        if (data.type === 'slice') {
	            // Get the total path length in order to use for dash array animation
	            var pathLength = data.element._node.getTotalLength();

	            // Set a dasharray that matches the path length as prerequisite to animate dashoffset
	            data.element.attr({
	                'stroke-dasharray': pathLength + 'px ' + pathLength + 'px'
	            });

	            // Create animation definition while also assigning an ID to the animation for later sync usage
	            var animationDefinition = {
	                'stroke-dashoffset': {
	                    id: 'anim' + data.index,
	                    dur: 1000,
	                    from: -pathLength + 'px',
	                    to: '0px',
	                    easing: Chartist.Svg.Easing.easeOutQuint,
	                    // We need to use `fill: 'freeze'` otherwise our animation will fall back to initial (not visible)
	                    fill: 'freeze'
	                }
	            };

	            // If this was not the first slice, we need to time the animation so that it uses the end sync event of the previous animation
	            if (data.index !== 0) {
	                animationDefinition['stroke-dashoffset'].begin = 'anim' + (data.index - 1) + '.end';
	            }

	            // We need to set an initial value before the animation starts as we are not in guided mode which would do that for us
	            data.element.attr({
	                'stroke-dashoffset': -pathLength + 'px'
	            });

	            // We can't use guided mode as the animations need to rely on setting begin manually
	            // See http://gionkunz.github.io/chartist-js/api-documentation.html#chartistsvg-function-animate
	            data.element.animate(animationDefinition, false);
	        }
	    });
	    
	});
</script>
							<div class="col-xl-3 col-lg-6 col-md-6 col-sm-12 col-12">
                                <div class="card">
                                    <h5 class="card-header"> 당일 판매처 송장 비율 </h5>
                                    <div class="card-body">
                                        <div class="ct-chart-category ct-golden-section" style="height: 315px;"></div>
                                        <div class="text-center m-t-40">
                                        	<c:if test="${empty deliveryList }">
                                        		<span class="legend-item mr-3">
	                                                <span class="fa-xs text-secondary mr-1 legend-tile"><i class="fa fa-fw fa-square-full "></i> 값이 없습니다 </span><span class="legend-text"></span>
	                                            </span>
                                        	</c:if>
                                        	<c:if test="${!empty deliveryList }">                                        	
	                                        	<c:forEach var="deliverylist" items="${deliveryList}">
													<span class="legend-item mr-3">
	                                                    <span class="fa-xs text-primary mr-1 legend-tile"><i class="fa fa-fw fa-square-full "> ${deliverylist.ss_name } </i></span><span class="legend-text"></span>
	                                            	</span>
												</c:forEach>
                                        	</c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>