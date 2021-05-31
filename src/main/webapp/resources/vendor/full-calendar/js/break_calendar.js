$(function() {

    $(document).ready(function() {
    	var date = new Date();
        
       var calendar =  $('#calendar1').fullCalendar({
            header: {
                left: 'prev today',
                center: 'title',
                right: 'month, next'
            },
            
            buttonText: {
            	today: '오늘',
            	month: '월별',
            	week: '주별'
            },
            defaultDate: date,
            contentHeight: 'auto',
            handleWindowResize : false,
            aspectRatio: 3.25,
            navLinks: false, // can click day/week names to navigate views
            editable: false,
            defaultView: "month",
            views:{ month:{ titleFormat: "YYYY년 MMMM" } },
            monthNames:['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            monthNamesShort:['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            dayNames:['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
            dayNamesShort:['일', '월', '화', '수', '목', '금', '토'],
            timeFormat: 'HH:mm'
            /*events: [{
                    title: 'All Day Event',
                    start: '2018-03-01',
                },
                {
                    title: 'Long Event',
                    start: '2018-03-07',
                    end: '2018-03-10'
                },
                {
                    id: 999,
                    title: 'Repeating Event',
                    start: '2018-03-09T16:00:00',
                    backgroundColor: '#ffc108',
                    borderColor: '#ffc108'

                },
                {
                    id: 999,
                    title: 'Repeating Event',
                    start: '2018-03-16T16:00:00',
                    backgroundColor: '#ffc108',
                    borderColor: '#ffc108'

                },
                {
                    title: 'Conference',
                    start: '2018-03-11',
                    end: '2018-03-13',
                    backgroundColor: '#ff407b',
                    borderColor: '#ff407b'

                },
                {
                    title: 'Meeting',
                    start: '2018-03-12T10:30:00',
                    end: '2018-03-12T12:30:00',
                    backgroundColor: '#25d5f2',
                    borderColor: '#25d5f2'
                },
                {
                    title: 'Lunch',
                    start: '2018-03-12T12:00:00',
                    backgroundColor: '#ff407b',
                    borderColor: '#ff407b'

                },
                {
                    title: 'Meeting',
                    start: '2018-03-12T14:30:00',
                    backgroundColor: '#25d5f2',
                    borderColor: '#25d5f2'
                },
                {
                    title: 'Happy Hour',
                    start: '2018-03-12T17:30:00'
                },
                {
                    title: 'Dinner',
                    start: '2018-03-12T20:00:00'
                },
                {
                    title: 'Birthday Party',
                    start: '2018-03-13T07:00:00',
                    backgroundColor: '#ef172c',
                    borderColor: '#ef172c'
                },
                {
                    title: 'Click for Google',
                    url: 'http://google.com/',
                    start: '2018-03-28',
                    backgroundColor: '#4285F4',
                    borderColor: '#4285F4'
                }
            ]*/
        });

       
    });
    
  
 });