package com.example.demo.repository;

import com.example.demo.entity.QItem;
import com.example.demo.entity.QReservation;
import com.example.demo.entity.QUser;
import com.example.demo.entity.Reservation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReservationRepositoryQueryImpl implements ReservationRepositoryQuery {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Reservation> searchReservations(Long userId, Long itemId) {
        QItem item = QItem.item;
        QUser user = QUser.user;
        QReservation reservation = QReservation.reservation;

        return queryFactory
                .selectFrom(reservation)
                .join(reservation.user, user).fetchJoin()
                .join(reservation.item, item).fetchJoin()
                .where(user.id.eq(userId).and(item.id.eq(itemId)))
                .fetch();
    }
}
