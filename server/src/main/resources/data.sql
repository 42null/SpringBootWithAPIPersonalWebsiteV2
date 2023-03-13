insert into customer(customer_id, name)
values (1, 'Acme Corporation');
insert into customer(customer_id, name)
values (2, 'Globex Corporation');
insert into customer(customer_id, name)
values (3, 'Soylent Corp');
insert into customer(customer_id, name)
values (4, 'Initech');
insert into customer(customer_id, name)
values (5, 'Umbrella Corporation');
insert into customer(customer_id, name)
values (6, 'Mom''s Friendly Robot Company');
-- http://chaoticshiny.com/spartgen.php
insert into product (product_id, name, cost)
values (1, 'Grease Link', 599.97);
insert into product (product_id, name, cost)
values (2, 'Iridium Authenticator Cord', 1287.99);
insert into product (product_id, name, cost)
values (3, 'Kamob Grip Shooter', 47.97);
insert into product (product_id, name, cost)
values (4, 'Thermal Terminal Magnet', 845.49);
insert into product (product_id, name, cost)
values (5, 'Blast Panel', 2157.98);
insert into product (product_id, name, cost)
values (6, 'AC Compressor Antenna', 219.87);
insert into product (product_id, name, cost)
values (7, 'Redundant Bolt Disrupter', 184.69);
insert into product (product_id, name, cost)
values (8, 'Plexiloop', 387.99);

insert into wholesale_order(purchase_order_num, terms, customer_id,
                product_id, purchase_date, shipped_date)
values('27B/6', 'Net 30', 1, 1,
       PARSEDATETIME('31 Oct 2019', 'dd MMM yyyy'),
       PARSEDATETIME('02 Nov 2019', 'dd MMM yyyy'));
insert into wholesale_order(purchase_order_num, terms, customer_id,
                  product_id, purchase_date, shipped_date)
    values('27B/5', 'Net 90', 1, 3,
           PARSEDATETIME('16 Jul 2019', 'dd MMM yyyy'),
           PARSEDATETIME('20 Jul 2019', 'dd MMM yyyy'));
insert into wholesale_order(purchase_order_num, terms, customer_id,
                  product_id, purchase_date, shipped_date)
    values('27B/4', 'Net 30', 1, 8,
           PARSEDATETIME('20 Jun 2019', 'dd MMM yyyy'),
           PARSEDATETIME('25 Jun 2019', 'dd MMM yyyy'));
-----------------------------
insert into wholesale_order(purchase_order_num, terms, customer_id,
                  product_id, purchase_date, shipped_date)
    values('ABC-123', '2/10 Net 30', 2, 2,
           PARSEDATETIME('31 Oct 2019', 'dd MMM yyyy'),
           PARSEDATETIME('02 Nov 2019', 'dd MMM yyyy'));
insert into wholesale_order(purchase_order_num, terms, customer_id,
                  product_id, purchase_date, shipped_date)
    values('XYZ-789', '2/10 Net 30',2, 4,
           PARSEDATETIME('16 Jul 2019', 'dd MMM yyyy'),
           PARSEDATETIME('20 Jul 2019', 'dd MMM yyyy'));
insert into wholesale_order(purchase_order_num, terms, customer_id,
                  product_id, purchase_date, shipped_date)
    values('ZZZ-000', 'COD',2, 5,
           PARSEDATETIME('20 Jun 2019', 'dd MMM yyyy'),
           PARSEDATETIME('25 Jun 2019', 'dd MMM yyyy'));
-----------------------------
insert into wholesale_order(purchase_order_num, terms, customer_id,
                  product_id, purchase_date, shipped_date)
    values('548-78', 'Net 60', 3, 7,
           PARSEDATETIME('31 Oct 2019', 'dd MMM yyyy'),
           PARSEDATETIME('02 Nov 2019', 'dd MMM yyyy'));
insert into wholesale_order(purchase_order_num, terms, customer_id,
                  product_id, purchase_date, shipped_date)
    values('987-11', 'Net 30',3, 2,
           PARSEDATETIME('16 Jul 2019', 'dd MMM yyyy'),
           PARSEDATETIME('20 Jul 2019', 'dd MMM yyyy'));
insert into wholesale_order(purchase_order_num, terms, customer_id,
                  product_id, purchase_date, shipped_date)
    values('547-35', 'Net 60',3, 6,
           PARSEDATETIME('20 Jun 2019', 'dd MMM yyyy'),
           PARSEDATETIME('25 Jun 2019', 'dd MMM yyyy'));
-----------------------------
insert into wholesale_order(purchase_order_num, terms, customer_id,
                  product_id, purchase_date, shipped_date)
    values('MAY-1234', 'COD', 4, 6,
           PARSEDATETIME('15 May 2019', 'dd MMM yyyy'),
           PARSEDATETIME('18 May 2019', 'dd MMM yyyy'));
insert into wholesale_order(purchase_order_num, terms, customer_id,
                  product_id, purchase_date, shipped_date)
    values('MAR-6517', 'Net 30', 4, 7,
           PARSEDATETIME('29 Mar 2019', 'dd MMM yyyy'),
           PARSEDATETIME('02 Apr 2019', 'dd MMM yyyy'));
insert into wholesale_order(purchase_order_num, terms, customer_id,
                  product_id, purchase_date, shipped_date)
    values('AUG-8871', 'COD', 4, 8,
           PARSEDATETIME('01 Aug 2019', 'dd MMM yyyy'),
           PARSEDATETIME('08 Aug 2019', 'dd MMM yyyy'));
-----------------------------
insert into wholesale_order(purchase_order_num, terms, customer_id,
                  product_id, purchase_date, shipped_date)
    values('65512', 'COD', 5, 3,
           PARSEDATETIME('26 Jun 2019', 'dd MMM yyyy'),
           PARSEDATETIME('27 Jun 2019', 'dd MMM yyyy'));
insert into wholesale_order(purchase_order_num, terms, customer_id,
                  product_id, purchase_date, shipped_date)
    values('65513', 'Net 30', 5, 4,
           PARSEDATETIME('16 Apr 2019', 'dd MMM yyyy'),
           PARSEDATETIME('20 Apr 2019', 'dd MMM yyyy'));
insert into wholesale_order(purchase_order_num, terms, customer_id,
                  product_id, purchase_date, shipped_date)
    values('65587', 'COD', 5, 2,
           PARSEDATETIME('21 Sep 2019', 'dd MMM yyyy'),
           PARSEDATETIME('26 Sep 2019', 'dd MMM yyyy'));
-----------------------------
insert into wholesale_order(purchase_order_num, terms, customer_id,
                  product_id, purchase_date, shipped_date)
    values('MOM1', 'Cash in Advance', 6, 5,
           PARSEDATETIME('26 Sep 2019', 'dd MMM yyyy'),
           PARSEDATETIME('26 Sep 2019', 'dd MMM yyyy'));
insert into wholesale_order(purchase_order_num, terms, customer_id,
                  product_id, purchase_date, shipped_date)
    values('MOM2', 'Cash in Advance',6, 3,
           PARSEDATETIME('01 Sep 2019', 'dd MMM yyyy'),
           PARSEDATETIME('05 Sep 2019', 'dd MMM yyyy'));
insert into wholesale_order(purchase_order_num, terms, customer_id,
                  product_id, purchase_date, shipped_date)
    values('MOM3', 'Net 60', 6, 1,
           PARSEDATETIME('16 Jun 2019', 'dd MMM yyyy'),
           PARSEDATETIME('18 Jun 2019', 'dd MMM yyyy'));


