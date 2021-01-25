import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { WbaHipsterTestModule } from '../../../test.module';
import { BngInfoUpdateComponent } from 'app/entities/bng-info/bng-info-update.component';
import { BngInfoService } from 'app/entities/bng-info/bng-info.service';
import { BngInfo } from 'app/shared/model/bng-info.model';

describe('Component Tests', () => {
  describe('BngInfo Management Update Component', () => {
    let comp: BngInfoUpdateComponent;
    let fixture: ComponentFixture<BngInfoUpdateComponent>;
    let service: BngInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WbaHipsterTestModule],
        declarations: [BngInfoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BngInfoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BngInfoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BngInfoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BngInfo(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new BngInfo();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
