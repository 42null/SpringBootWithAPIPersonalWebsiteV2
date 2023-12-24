$(function () {

    const $sorts = $('#results-members>thead>tr#sorting>th');
    const $filters = $('#results-members thead tr:nth-child(1) th input');

    const $filterFields = $('#results-members thead tr:nth-child(1) th input');

    const $filterId = $('#filter_id > input');
    const $filterUserName = $('#filter_userName > input');
    const $filterFirstName = $('#filter_firstName > input');
    const $filterLastName = $('#filter_lastName > input');
    const $filterEnrollmentDate = $('#filter_enrollmentDate > input');
    const $filterCancellationDate = $('#filter_cancellationDate > input');
    const $filterPoolAccess = $('#filter_poolAccess > input');
    const $filterSpaAccess = $('#filter_spaAccess > input');

    const $resultsElementMembers = $('#results-members > tbody');

    const $newId = $('#member-new_id');
    const $newUserName = $('#member-new_userName');
    const $newFirstName = $('#member-new_firstName');
    const $newLastName = $('#member-new_lastName');
    const $newEnrollmentDate = $('#member-new_enrollmentDate');
    const $newCancellationDate = $('#member-new_cancellationDate');
    const $newPoolAccess = $('#member-new_poolAccess');
    const $newSpaAccess = $('#member-new_spaAccess');

    const $addNewMemberForm = $('#add-member-form');

    let $deletes = $('span.delete');

    // Verifications
    // ID - six numbers
    $newId.on('keyup blur', e => {
        const $target = $(e.target);
        if (/^[0-9]{6}$/.test($target.val()) /*|| ($target.val().length === 0)*/) {
            $target.removeClass('error')
        } else {
            $target.addClass('error')
        }
        // console.log($target.val().length);
    });
    // User Name - two numbers followed by four to six letters
    $newUserName.on('keyup blur', e => {
        const $target = $(e.target);
        if (/^[0-9]{2}[a-z]{4,6}$/.test($target.val())) {
            $target.removeClass('error')
        } else {
            $target.addClass('error')
        }
    });
    // First Name - lowercase and/or uppercase
    $newFirstName.on('keyup blur', e => {
        const $target = $(e.target);
        if (/^[A-Z]{0,1}[a-z^ ]{1,}$/.test($target.val())) {
            $target.removeClass('error')
        } else {
            $target.addClass('error')
        }
    });
    // Last Name - lowercase and/or uppercase
    $newLastName.on('keyup blur', e => {
        const $target = $(e.target);
        if (/^[A-Z]{0,1}[a-z^ ]{1,}$/.test($target.val())) {
            $target.removeClass('error')
        } else {
            $target.addClass('error')
        }
    });
    // Enrollment Date - new users can only be enrolled on or before the current date
    $newEnrollmentDate.attr('min', ((new Date().getFullYear()) + "-" + (new Date().getMonth() + 1) + "-" + (new Date().getDate())));//Format gained from https://www.w3schools.com/jsref/prop_date_min.asp
    // Cancellation Date - no validation needed but added validation to also be after the enrollment
    $newEnrollmentDate.on('change', function (e) {
        $newCancellationDate.attr('min', $newEnrollmentDate.val());
    });


    class Member {
        constructor(id, userName, firstName, lastName, enrollmentDate, cancellationDate, poolAccess, spaAccess) {
            this.id = id;
            this.userName = userName;
            this.firstName = firstName;
            this.lastName = lastName;
            this.enrollmentDate = enrollmentDate;
            this.cancellationDate = cancellationDate;
            this.poolAccess = poolAccess;
            this.spaAccess = spaAccess;

            this.htmlCard = null;
        }
        formatDate(date) {
            if (date == null) {
                return "";
            } else if (typeof date === "string") {
                date = new Date(date);
            }
            return (date.getMonth() + 1) + "/" + (date.getDate()) + "/" + (date.getFullYear());
        }
        formatId(id) {
            id = id + "";
            while (id.length < 6) {
                id = 0 + id;
            }
            return id;
        }
        generateHtml() {
            const memberCard = document.createElement("tr");
            memberCard.classList.add("card");
            memberCard.innerHTML =
                `
                    <td>${this.formatId(this.id)}</td>
                    <td>${this.userName}</td>
                    <td>${this.firstName}</td>
                    <td>${this.lastName}</td>
                    <td>${this.formatDate(this.enrollmentDate)}</td>
                    <td>${this.formatDate(this.cancellationDate)}</td>
                    <td>${(this.poolAccess ? `<span class="material-symbols-outlined allowed"></span>` : ``)}</td>
                    <td>${(this.spaAccess ? `<span class="material-symbols-outlined allowed"></span>` : ``)}</td>
                    <td class="action"><span class="material-symbols-outlined delete"></span></td>
                `;
            return memberCard;// Type: HtmlElement
        }

        setCardVisabality(visiable) {
            if (visiable) {
                this.htmlCard.classList.remove('hidden');
            } else {
                this.htmlCard.classList.add('hidden');
            }
        }
        getHtmlCard() {
            if (this.htmlCard === null) {
                this.htmlCard = this.generateHtml();
            }
            return this.htmlCard;
        }
    };


    const members = [
        // Dummy data for prepopulated data generated using ChatGPT 3.5
        new Member(1, "12alice", "Alice", "Smith", new Date('01-01-2019'), new Date('2020-05-01'), true, false),
        new Member(2, "34bobJ", "Bob", "Johnson", new Date('03-15-2019'), new Date('2021-02-20'), false, true),
        new Member(3, "56charli", "Charlie", "Davis", new Date('06-30-2019'), new Date('2022-09-10'), true, true),
        new Member(4, "78david", "David", "Miller", new Date('09-20-2019'), null, true, false),
        new Member(5, "91evaw", "Eva", "Wilson", new Date('01-10-2020'), new Date('2022-11-05'), false, true),
        new Member(6, "23frank", "Frank", "Brown", new Date('04-25-2020'), new Date('2023-01-15'), true, false),
        new Member(7, "45grace", "Grace", "Taylor", new Date('08-05-2020'), null, false, true),
        new Member(8, "67harry", "Harry", "Anderson", new Date('11-15-2020'), new Date('2023-04-02'), true, false),
        new Member(9, "89tvyMa", "Ivy", "Martinez", new Date('03-02-2021'), null, true, true),
        new Member(10, "12jack", "Jack", "Turner", new Date('06-20-2021'), new Date('2023-07-10'), false, false),
        new Member(11, "34kevin", "Kevin", "White", new Date('09-15-2021'), null, true, false),
        new Member(12, "56lily", "Lily", "Clark", new Date('01-05-2022'), new Date('2023-11-20'), false, true),
        new Member(13, "78mark", "Mark", "Harris", new Date('04-18-2022'), null, true, true),
        new Member(14, "91nora", "Nora", "Wright", new Date('08-10-2022'), new Date('2023-09-15'), true, false),
        new Member(15, "23Oliv", "Olivia", "Robinson", new Date('11-30-2022'), null, false, true),
    ];
    let sortedMembers = [];

    members.forEach(member => {
        addNewMember(member.id, member.userName, member.firstName, member.lastName, member.enrollmentDate, member.cancellationDate, member.poolAccess, member.spaAccess, true);
    });

    function postMembers(localMembers) {
        $resultsElementMembers.find(".card").remove();
        localMembers.forEach(member => {
            //I have it set up sorta like a singleton where if it doesn't already exist, it will generate it new
            $resultsElementMembers.append(member.getHtmlCard());
            member.setCardVisabality(true);
        });
    }

    function unFilterAllCardsResults() {
        members.forEach(typeInstance => {
            typeInstance.setCardVisabality(true);
        });
    }

    $filterPoolAccess.on('change', function (e) {
        if ($filterPoolAccess.hasClass('indeterminate')) {
            $filterPoolAccess.addClass('checked')
            $filterSpaAccess.attr('checked', false);
            $filterPoolAccess.removeClass('indeterminate')
            // https://css-tricks.com/indeterminate-checkboxes/
            $filterPoolAccess.prop("indeterminate", false);
        } else if ($filterPoolAccess.hasClass('checked')) {
            $filterPoolAccess.addClass('unchecked')
            $filterSpaAccess.attr('checked', false);
            $filterPoolAccess.removeClass('checked')
        } else {
            $filterPoolAccess.removeClass('unchecked')
            $filterPoolAccess.addClass('indeterminate')
            $filterSpaAccess.attr('checked', false);
            $filterPoolAccess.prop("indeterminate", true);
        }
    });
    $filterSpaAccess.on('change', function (e) {
        // $filterSpaAccess.preventDefault();
        if ($filterSpaAccess.hasClass('indeterminate')) {
            $filterSpaAccess.addClass('checked')
            $filterSpaAccess.attr('checked', false);
            $filterSpaAccess.removeClass('indeterminate')
            $filterSpaAccess.prop("indeterminate", false);
        } else if ($filterSpaAccess.hasClass('checked')) {
            $filterSpaAccess.addClass('unchecked')
            $filterSpaAccess.attr('checked', false);
            $filterSpaAccess.removeClass('checked')
        } else {
            $filterSpaAccess.removeClass('unchecked')
            $filterSpaAccess.addClass('indeterminate')
            $filterSpaAccess.attr('checked', false);
            $filterSpaAccess.prop("indeterminate", true);
        }
    });

    function filterMembers() {
        const filteredMembers = members.filter(function (member) {
            let filterId = $filterId.val();
            if (filterId < 0) {
                filterId = "";
                $filterId.val("");
            }
            const filterUserName = $filterUserName.val().toLowerCase();
            const filterFirstName = $filterFirstName.val().toLowerCase();
            const filterLastName = $filterLastName.val().toLowerCase();
            const filterEnrollmentDate = new Date($filterEnrollmentDate.val().replaceAll("-", "/"));
            const filterCancellationDate = new Date($filterCancellationDate.val().replaceAll("-", "/"));
            // const filterPoolAccess = $filterPoolAccess.val();
            // const filterSpaAccess = $filterSpaAccess.val();

            if (
                (member.id + "").includes(filterId) &&
                (member.userName.toLowerCase() + "").includes(filterUserName) &&
                (member.firstName.toLowerCase() + "").includes(filterFirstName) &&
                (member.lastName.toLowerCase() + "").includes(filterLastName) &&
                (!$filterEnrollmentDate.val() || (member.enrollmentDate.getDate() === filterEnrollmentDate.getDate() && member.enrollmentDate.getMonth() === filterEnrollmentDate.getMonth() && member.enrollmentDate.getFullYear() === filterEnrollmentDate.getFullYear())) &&
                (!$filterCancellationDate.val() || (member.cancellationDate.getDate() === filterCancellationDate.getDate() && member.cancellationDate.getMonth() === filterCancellationDate.getMonth() && member.cancellationDate.getFullYear() === filterCancellationDate.getFullYear())) &&
                ($filterPoolAccess.hasClass('indeterminate')
                    || (member.poolAccess && ($filterPoolAccess.hasClass('checked')))
                    || ((!member.poolAccess) && ($filterPoolAccess.hasClass('unchecked')))) &&
                ($filterSpaAccess.hasClass('indeterminate')
                    || (member.spaAccess && ($filterSpaAccess.hasClass('checked')))
                    || ((!member.spaAccess) && ($filterSpaAccess.hasClass('unchecked'))))
            ) {
                return true;
            }
            return false;

        });
        return filteredMembers;
    }

    function sortMembers(sortBy, direction) {
        const filteredMembers = filterMembers();
        if (direction === 0) {
            return sortMembers("sort_id", -1);
        }

        filteredMembers.sort(function (memberA, memberB) {
            let aValue;
            let bValue;
            switch (sortBy) {
                case "sort_id":
                    aValue = memberA.id;
                    bValue = memberB.id;
                    break;
                case "sort_userName":
                    aValue = memberA.userName;
                    bValue = memberB.userName;
                    break;
                case "sort_firstName":
                    aValue = memberA.firstName;
                    bValue = memberB.firstName;
                    break;
                case "sort_lastName":
                    aValue = memberA.lastName;
                    bValue = memberB.lastName;
                    break;
                case "sort_enrollmentDate":
                    aValue = memberA.enrollmentDate;
                    bValue = memberB.enrollmentDate;
                    break;
                case "sort_cancellationDate":
                    aValue = memberA.cancellationDate;
                    bValue = memberB.cancellationDate;
                    break;
                case "sort_poolAccess":
                    aValue = memberA.poolAccess;
                    bValue = memberB.poolAccess;
                    break;
                case "sort_spaAccess":
                    aValue = memberA.spaAccess;
                    bValue = memberB.spaAccess;
                    break;
                default:
                    return;
            }
            if (aValue > bValue) {
                return 1;
            } else if (aValue < bValue) {
                return -1;
            }
            return 0;
        });

        if (direction === 1) {
            sortedMembers = filteredMembers.reverse();
            // }else if(direction === -1){
        } else {
            sortedMembers = filteredMembers;
        }
        return sortedMembers;
    }

    // APPLY TO ALL INSTANCES

    function resetSortIcons() {
        $sorts.each(function (i, element) {
            const $element = $(element).closest('th');
            $element.removeClass("sort-accending");
            $element.removeClass("sort-decending");
        });
    }

    // Every sort field header trigger
    $sorts.each(function (i, element) {
        $(this).closest('th').on('click', function (e) {
            const $target = $(e.target);
            let direction = 0;
            if ($target.hasClass('sort-accending')) {
                resetSortIcons();
                $target.addClass('sort-decending');
                direction = 1;
            } else if ($target.hasClass('sort-decending')) {
                resetSortIcons();
            } else {
                resetSortIcons();
                $target.addClass('sort-accending');
                direction = -1;
            }
            postMembers(sortMembers($target.attr('name'), direction));
        });
    });

    // Sort on edit search fields
    $filterFields.on('change keyup', function () {
        console.log("Starting filter & sort");
        let showMembers = filterMembers();
        postMembers(showMembers);
        console.log("Done with filter & sort");
        updateDeletes();
    });

    const $forms = $('form');
    $forms.on('submit', e => {
        e.preventDefault();
    });

    // Make all *'s in labels red for required
    $('label').each(function (i, labelElement) {
        if (labelElement.textContent.endsWith('*:')) {
            labelElement.textContent = labelElement.textContent.substring(0, labelElement.textContent.length - 2);
            labelElement.innerHTML += `<span class="requiredTextStar">*</span>:`
        }
    });


    // Add Member
    $addNewMemberForm.on('submit', function (e) {
        addNewMember($newId.val(), $newUserName.val(), $newFirstName.val(), $newLastName.val(), $newEnrollmentDate.val(), $newCancellationDate.val(), $newPoolAccess.val(), $newSpaAccess.val());
        $filterFields.first().trigger("change");
        updateDeletes();
        $addNewMemberForm.trigger("reset");
    });

    function addNewMember(newId, newUserName, newFirstName, newLastName, newEnrollmentDate, newCancellationDate, newPoolAccess, newSpaAccess, preventPush) {
        const newMember = new Member(newId, newUserName, newFirstName, newLastName, newEnrollmentDate, newCancellationDate, newPoolAccess, newSpaAccess);
        if (!preventPush) {
            members.push(newMember);
        }
    }

    function updateDeletes() {
        $deletes = $('span.material-symbols-outlined.delete');
        // console.log($deletes);
        $deletes.on('click', function (e) {
            console.log("Removing member");
            const $targetCard = $(e.target).closest('.card');
            let targetId = $targetCard.find('td').first().text();
            while (targetId.substring(0, 1) == "0") {
                targetId = targetId.substring(1);
            }
            targetId = Number(targetId);
            let targetMember = new Member();//999999, "", "", "", new Date('01-01-2999'), null, false, false);
            members.forEach(member => {
                if (targetId === member.id) {
                    targetMember = member;
                }
            });
            members.splice(members.indexOf(targetMember), 1);
            console.log(members);
            $targetCard.remove();
        });
    }
    updateDeletes();

    // Run once on page load to startup values
    $filterFields.first().trigger("change");
    $filterPoolAccess.prop("indeterminate", true);
    $filterSpaAccess.prop("indeterminate", true);

});