$(document).ready(function () 
    {
    $('.myImages').first().addClass('active');
    $('.myImages').hide();
    $('.active').show();

    $('#nextButton').click(function () 
    {
        $('.active').removeClass('active').addClass('previouslyActive');
        if ($('.previouslyActive').is(':last-child')) 
        {
            $('.myImages').first().addClass('active');
        } 
        else 
        {
            $('.previouslyActive').next().addClass('active');
        }

        $('.previouslyActive').removeClass('previouslyActive');
        $('.myImages').fadeOut();
        $('.active').fadeIn();
    });


    //Ref: http://jsfiddle.net/HsEne/15/
    $('#previousButton').click(function () {
        
        $('.active').removeClass('active').addClass('previouslyActive');
        if ($('.previouslyActive').is(':first-child')) {
            $('.myImages').last().addClass('active');
        } 
        else 
        {
            $('.previouslyActive').prev().addClass('active');
        }
        $('.previouslyActive').removeClass('previouslyActive');
        $('.myImages').fadeOut();
        $('.active').fadeIn();
    });

$('#button1').click(function () 
{
    $('.active').removeClass('active');
    $('.myImages').first().addClass('active');
    $('.myImages').hide();
    $('.active').show();
});

$('#button2').click(function () 
{
    $('.active').removeClass('active');
    $('.myImages:nth-child(2)').addClass('active');
    $('.myImages').hide();
    $('.active').show();
});

$('#button3').click(function () 
{
   
    $('.active').removeClass('active');
    $('.myImages:nth-child(3)').addClass('active');
    $('.myImages').hide();
    $('.active').show();
});

$('#button4').click(function () 
{
    $('.active').removeClass('active');
    $('.myImages:nth-child(4)').addClass('active');
    $('.myImages').hide();
    $('.active').show();
});

$('#button5').click(function () 
{
   
    $('.active').removeClass('active');
    $('.myImages:nth-child(5)').addClass('active');
    $('.myImages').hide();
    $('.active').show();
});

$('#button6').click(function () 
{
   
    $('.active').removeClass('active');
    $('.myImages:nth-child(6)').addClass('active');
    $('.myImages').hide();
    $('.active').show();
});


});


